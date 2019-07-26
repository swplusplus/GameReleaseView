<template>
  <section>
    <intervalSetter />
    <filters />
    <tileCalendar/>
  </section>
</template>

<script>
import intervalSetter from "@/components/IntervalSetter";
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
    intervalSetter,
    oneday,
    tileCalendar,
    filters
  },
  methods: {
  }
};
</script>
