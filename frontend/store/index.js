import Vuex from "vuex";
import axios from "axios";
import moment from "moment"
import { type } from "os";

function pad(n) {
    return n < 10 ? '0' + n : n;
}

export const state = () => ({
    releases: [],
    intervalStart: moment().startOf("day"),
    intervalEnd: moment().startOf("day").add(1, "week"),
    excludedFilters: [],
    includedFilters: []
});

export const mutations = {
    setReleases(state, rel) {
        state.releases = rel;
    },
    setInterval(state, interval) {
        state.intervalStart = moment(interval.startDate).startOf("day");
        state.intervalEnd = moment(interval.endDate).startOf("day");
    },
    setExcludedFilters(state, filters) {
        state.excludedFilters = filters;
    },
    setIncludedFilters(state, filters) {
        state.includedFilters = filters;
    },
    setReleaseVisible(state, vis) {
        state.releases.find(x => x.id == vis.id).visible = vis.visible;
    }
}

export const actions = {
    nuxtServerInit(vuexContext, context) {
        var targetDate = moment.utc().startOf("day");
        targetDate.add(1, "months");
        const request = "http://localhost:8080/releases?exact_dates_only=true&to=" + targetDate.year() + "-" + pad(targetDate.month() + 1) + "-" + pad(targetDate.date());
        console.log(request)
        return axios.get(request)
            .then(res => {
                var releasesArray = [];
                for (var releaseObj of res.data.releases) {
                    releaseObj.dateFrom = moment(releaseObj.date_from).startOf("day");
                    releaseObj.dateTo = moment(releaseObj.date_to).startOf("day");
                    releaseObj.visible = true;
                    releasesArray.push(releaseObj);
                }
                vuexContext.dispatch("setReleases", releasesArray)
            })
            .catch(e => context.error(e))
    },
    setReleases(vuexContext, releases) {
        vuexContext.commit("setReleases", releases);
    },
    updateVisibleReleases(vuexContext) {
        var releases = vuexContext.getters.getCurrentIntervalReleases;
        const excludedFilters = vuexContext.getters.getExcludedFilters;
        const includedFilters = vuexContext.getters.getIncludedFilters;

        const hasIncludes = includedFilters.findIndex(catFilters => {return catFilters.values.length > 0;}) != -1;

        for (var releaseIdx in releases) {
            var release = releases[releaseIdx];
            var visible = false;
            if (hasIncludes) {
                visible = includedFilters.findIndex(catFilters => {
                    return catFilters.values.findIndex(filter => {
                        return filter.games.includes(release.id);}) != -1;
                }) != -1;
            } else {
                var invisible = [];
                visible = invisible.findIndex(id => {return id == release.id;}) == -1 
                            && excludedFilters.findIndex(catFilters => {
                                return catFilters.values.findIndex(filter => {
                                    return filter.games.includes(release.id);}) == -1;
                            }) != -1;
                if (!visible) {
                    invisible.push(release.id);
                }
            }
            vuexContext.commit("setReleaseVisible", {id: release.id, visible: visible});
        }
    },
    setViewInterval(vuexContext, interval) {
        vuexContext.commit("setInterval", interval);
        vuexContext.dispatch("updateVisibleReleases");
    },
    setCategoryFilters(vuexContext, categoryFilters) {
        var excludedFilters = JSON.parse(JSON.stringify(vuexContext.getters.getExcludedFilters));
        var index = excludedFilters.findIndex(e => {return e.category === categoryFilters.category;});
        if (index == -1) {
            excludedFilters.push({category: categoryFilters.category, values: []});
            index = excludedFilters.length - 1;
        }
        excludedFilters[index].values = categoryFilters.excluded;
        vuexContext.commit("setExcludedFilters", excludedFilters);

        var includedFilters = JSON.parse(JSON.stringify(vuexContext.getters.getIncludedFilters));
        var index = includedFilters.findIndex(e => {return e.category === categoryFilters.category;});
        if (index == -1) {
            includedFilters.push({category: categoryFilters.category, values: []});
            index = includedFilters.length - 1;
        }
        includedFilters[index].values = categoryFilters.included;
        vuexContext.commit("setIncludedFilters", includedFilters);
        vuexContext.dispatch("updateVisibleReleases");
    }
}

export const getters = {
    getReleases: (state) => {
        return state.releases;
    },
    getIntervalStart: (state) => {
        return state.intervalStart;
    },
    getIntervalEnd: (state) => {
        return state.intervalEnd;
    },
    getExcludedFilters: (state) => {
        return state.excludedFilters;
    },
    getIncludedFilters: (state) => {
        return state.includedFilters;
    },
    getCurrentIntervalReleases: (state, getters) => {
        return getters.getReleasesBetween(moment(getters.getIntervalStart).startOf("day"), moment(getters.getIntervalEnd).startOf("day"));
    },
    getVisibleReleaseByDate: (state) => (date) => {
        var filtered = state.releases.filter(release => {
            return release.visible && moment(date).isBetween(release.dateFrom, release.dateTo, null, "[]");
        });
        return filtered;
    },
    getReleasesBetween: (state) => (startDate, endDate) => {
        var filtered = state.releases.filter(release => {
            return moment(release.dateFrom).isBetween(startDate, endDate, null, "[]") || moment(release.dateTo).isBetween(startDate, endDate, null, "[]") || moment(startDate).isBetween(release.dateFrom, release.dateTo, null, "[]") || moment(endDate).isBetween(release.dateFrom, release.dateTo, null, "[]");
        });
        return filtered;
    },
    getAttrFilters: (state, getters) => {
        var attrs = {
            attributes: []
            // {
            //     category: "",
            //     values: [{category_value: "", games: []}]
            // }
        }

        var releases = getters.getCurrentIntervalReleases;
        for (var release in releases) {
            for (var attr in releases[release].filter_attrs) {
                if (releases[release].filter_attrs[attr] != null) {
                    var index = attrs.attributes.findIndex(entry => entry.category === attr);
                    if (index === -1) {
                        attrs.attributes.push({ category: attr, values: [] });
                        index = attrs.attributes.length - 1;
                    }
                    if (releases[release].filter_attrs[attr].constructor == Array) {
                        for (var i = 0; i < releases[release].filter_attrs[attr].length; ++i) {
                            var entry = releases[release].filter_attrs[attr][i];
                            putToValueArray(attrs.attributes[index].values, entry, releases[release].id);
                        }
                    } else {
                        putToValueArray(attrs.attributes[index].values, releases[release].filter_attrs[attr].toString(), releases[release].id);
                    }
                }
            }
        }
        return attrs.attributes;
    }
}

function putToValueArray(array, entry, game) {
    if (typeof (entry) !== "string") {
        console.error("attribute entries must all be strings (" + entry + " is " + typeof (entry) + ")");
        return;
    }
    var valueIndex = array.findIndex(e => e.category_value === entry)
    if (valueIndex === -1) {
        array.push({ category_value: entry, games: [] });
        valueIndex = array.length - 1;
    }
    array[valueIndex].games.push(game);
}
