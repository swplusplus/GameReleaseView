import Vue from "vue";
import moment from "moment";

Vue.filter('formatDate', function (value) {
    if (value) {
        return moment(value).format('YYYY-MM-DD');
    }
});

