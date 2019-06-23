import Vuex from "vuex";
import axios from "axios";
import moment from "moment"

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
        return axios.get(request)
            .then(res => {
                var releasesArray = [];
                for (const release of res.data.releases) {
                    releasesArray.push({
                        dateFrom: moment.utc(release.date_from).startOf("day"),
                        dateTo: moment.utc(release.date_to).startOf("day"),
                        name: release.name,
                        id: release.id,
                        originalDate: release.original_release_string
                    });
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
        var filtered = state.releases.filter(release => { return moment(release.dateFrom) <= date && moment(release.dateTo) >= date });
        return filtered;
    }
}
