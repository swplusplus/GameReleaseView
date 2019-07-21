import Vuex from "vuex";
import axios from "axios";
import moment from "moment"
import { type } from "os";

function pad(n) {
    return n < 10 ? '0' + n : n;
}

export const state = () => ({
    releases: []
});

export const mutations = {
    setReleases(state, rel) {
        state.releases = rel;
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
                    releaseObj.dateFrom = moment.utc(release.date_from).startOf("day");
                    releaseObj.dateTo = moment.utc(release.date_to).startOf("day");
                    releasesArray.push(releaseObj);
                }
                vuexContext.dispatch("setReleases", releasesArray)
            })
            .catch(e => context.error(e))
    },
    setReleases(vuexContext, releases) {
        vuexContext.commit("setReleases", releases);
    }
}

export const getters = {
    getReleases(state) {
        return state.releases;
    },
    getReleaseByDate: (state) => (date) => {
        var filtered = state.releases.filter(release => {
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
    getAttrFiltersBetween: (state, getters) => (startDate, endDate) => {
        console.log("COMPUTING filter attributes!");
        var attrs = {
            attributes: []
            // {
            //     category: "",
            //     values: [{category_value: "", games: []}]
            // }
        }

        var releases = getters.getReleasesBetween(startDate, endDate)
        console.log(releases.length + " releases")
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
