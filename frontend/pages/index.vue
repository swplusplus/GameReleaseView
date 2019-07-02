<template>
  <section>
    <tileCalendar :month="currentMonth()" />
  </section>
</template>

<script>
import oneday from "@/components/OneDay";
import tileCalendar from "@/components/TileCalendar";
import moment from "moment";
export default {
  metaInfo: {
    htmlAttrs: {
      class: "has-navbar-fixed-top"
    }
  },
  components: {
    oneday,
    tileCalendar
  },
  methods: {
    computeDate(daysFromToday) {
      return moment()
        .utc()
        .startOf("day")
        .add(daysFromToday, "days");
    },
    currentMonth() {
      return moment()
        .utc()
        .date(1);
    }
  }
};
</script>
