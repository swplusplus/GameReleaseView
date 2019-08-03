<template>
  <section>
    <p class="buttons">
      <a class="button is-danger is-outlined">
    <span>Delete</span>
    <span class="icon is-small">
      <i class="fas fa-times"></i>
    </span>
  </a>
    </p>
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
