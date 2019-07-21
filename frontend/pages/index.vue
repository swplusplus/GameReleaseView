<template>
  <section>
    <filters :dateFrom="computeDate(0)" :dateTo="computeDate(30)" />
    <tileCalendar :month="currentMonth()" />
  </section>
</template>

<script>
import oneday from "@/components/OneDay";
import tileCalendar from "@/components/TileCalendar";
import moment from "moment";
import filters from "@/components/Filters";
export default {
  metaInfo: {
    htmlAttrs: {
      class: "has-navbar-fixed-top"
    }
  },
  components: {
    oneday,
    tileCalendar,
    filters
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
