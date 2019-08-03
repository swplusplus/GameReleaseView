<template>
  <div>
    <input ref="calendarTriggerStart" type="date" class="input" data-is-range="true" show-buttons="false"></input>
  </div>
</template>

<script>
import moment from "moment";
if (process.client) {
  const bulmaCalendar = require('bulma-calendar/dist/js/bulma-calendar.js');
}
//import bulmaCalendar from "bulma-calendar/dist/js/bulma-calendar.js";
export default {
  data() {
    return {
      dateStart: moment(this.$store.getters.getIntervalStart),
      dateEnd: moment(this.$store.getters.getIntervalEnd)
    };
  },
  methods: {
    setInterval: function() {
      this.$store.dispatch("setViewInterval", {
        startDate: this.dateStart,
        endDate: this.dateEnd
      });
    }
  },
   mounted() {
     if (process.client) {
      const bulmaCalendar = require('bulma-calendar/dist/js/bulma-calendar.js');
      const calendar = bulmaCalendar.attach(
        this.$refs.calendarTriggerStart,
        {
          startDate: this.dateStart.toDate(),
          endDate: this.dateEnd.toDate()
        }
      )[0];
      calendar.on(
        "select",
        e => {
          this.dateStart = moment(e.data.startDate);
          this.dateEnd = moment(e.data.endDate);
          this.setInterval();
          }
      );
    }
   }
};
</script>