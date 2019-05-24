import Vuex from "vuex";

const createReleasesStore = () => {
    return new Vuex.Store({
        state: {
            releases: []
        },
        mutations: {
            setReleases(state, rel) {
                state.releases = rel;
            }
        },
        actions: {
            nuxtServerInit(vuexContext, posts) {
                return new Promise((resolve, reject) => {
                    // fetch data from rest api.
                    fetchedReleases = [];
                    vuexContext.commit("setReleases", fetchedReleases);
                    resolve();
                });
            },
            setReleases(vuexContext, posts) {
                vuexContext.commit("setPosts", posts);
            }
        },
        getters: {
            getReleases(state) {
                return state.releases;
            }
        }
    });
};
