import Vuex from "vuex";
import axios from "axios";
import moment from "moment"
import { type } from "os";

function pad(n) {
    return n < 10 ? '0' + n : n;
}

export const state = () => ({
    releases: [],
    visible_releases: [],
    intervalStart: moment(),
    intervalEnd: moment()
});

export const mutations = {
    setReleases(state, rel) {
        state.releases = rel;
    },
    setVisibleReleases(state, vis) {
        state.visible_releases = vis;
    },
    setInterval(state, interval) {
        state.intervalStart = moment(interval.startDate).startOf("day");
        state.intervalEnd = moment(interval.endDate).startOf("day");
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
                for (const release of res.data.releases) {
                    var releaseObj = release
                    releaseObj.dateFrom = moment(release.date_from).startOf("day");
                    releaseObj.dateTo = moment(release.date_to).startOf("day");
                    releasesArray.push(releaseObj);
                }
                vuexContext.dispatch("setReleases", releasesArray)
            })
            .catch(e => context.error(e))
    },
    setReleases(vuexContext, releases) {
        vuexContext.commit("setReleases", releases);
    },
    setVisibleReleases(vuexContext, releases) {
        vuexContext.commit("setVisibleReleases", releases);
    },
    setViewInterval(vuexContext, interval) {
        const releases = vuexContext.getters.getReleasesBetween(moment(interval.startDate).startOf("day"), moment(interval.endDate).startOf("day"));
        vuexContext.dispatch("setVisibleReleases", releases);
        vuexContext.commit("setInterval", interval);
        console.log(interval.startDate);
    }
}

export const getters = {
    getReleases: (state) => {
        return state.releases;
    },
    getVisibleReleases: (state) => {
        return state.visible_releases;
    },
    getIntervalStart: (state) => {
        return state.intervalStart;
    },
    getIntervalEnd: (state) => {
        return state.intervalEnd;
    },
    getVisibleReleaseByDate: (state) => (date) => {
        var filtered = state.visible_releases.filter(release => {
            return moment(date).isBetween(release.dateFrom, release.dateTo, null, "[]");
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
        console.log("COMPUTING filter attributes!");
        var attrs = {
            attributes: []
            // {
            //     category: "",
            //     values: [{category_value: "", games: []}]
            // }
        }

        var releases = getters.getVisibleReleases;
        console.log(releases);
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
