<template>
  <div>
    Selected date: { { niceDateStart } }
    <button ref="calendarTriggerStart" type="button">Change</button>
    <!-- Selected date: { { niceDate } }
    <button ref="calendarTriggerEnd" type="button">Change</button>-->
  </div>
</template>

<script>
import moment from "moment";
import bulmaCalendar from "bulma-calendar/dist/js/bulma-calendar.min.js";
export default {
  data() {
    return {
      dateStart: moment(),
      dateEnd: moment().add(1, "week")
    };
  },
  methods: {
    setInterval: function(startDate, endDate) {
      this.$store.dispatch("setInterval", {
        startDate: startDate,
        endDate: endDate
      });
    }
  },
  mounted() {
    if (process.client) {
      const calendarStart = bulmaCalendar.attach(
        this.$refs.calendarTriggerStart,
        {
          startDate: this.date
        }
      )[0];
      calendarStart.on(
        "date:selected",
        e => (this.startDate = moment(e.start) || null)
      );
      this.setInterval();
    }
  },
  computed: {
    niceDateStart() {
      if (this.date) {
        return this.date.toLocaleDateString();
      }
    },
    niceDateEnd() {
      if (this.date) {
        return this.date.toLocaleDateString();
      }
    }
  }
};
</script>