<template>
  <div>
    <input ref="calendarTriggerStart" type="date" class="input" data-is-range="true" show-buttons="false"></input>
  </div>
</template>

<script>
import moment from "moment";
if (process.client) {
  const bulmaCalendar = require('bulma-calendar/dist/js/bulma-calendar.js');
  console.log(bulmaCalendar);
}
//import bulmaCalendar from "bulma-calendar/dist/js/bulma-calendar.js";
export default {
  data() {
    return {
      dateStart: moment(),
      dateEnd: moment().add(1, "week")
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
       this.setInterval();
      const bulmaCalendar = require('bulma-calendar/dist/js/bulma-calendar.js');
      console.log(this);
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
          console.log(typeof(e.data.startDate));
          console.log(e.data.endDate);
          this.dateStart = moment(e.data.startDate);
          this.dateEnd = moment(e.data.endDate);
          this.setInterval();
          }
      );
    }
   }
};
</script>