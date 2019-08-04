<template>
  <section>
    <p class="buttons">
      <!-- <a class="button is-danger is-outlined">
        <span>Delete</span>
        <span class="icon is-small">
          <i class="fas fa-times"></i>
        </span>
      </a>-->
    </p>
    <intervalSetter />
    <filters />
    <section class="hero is-primary">
      <div class="hero-body">
        <div class="container">
          <button
            class="button is-outlined"
            @click="changeViewMode"
          >Change view mode to {{viewModeButtonText}}</button>
        </div>
      </div>
    </section>
    <tileCalendar v-show="displayCalendar" />
    <releaseTimeline v-show="displayTimeline" />
  </section>
</template>

<script>
import intervalSetter from "@/components/IntervalSetter";
import oneday from "@/components/OneDay";
import tileCalendar from "@/components/TileCalendar";
import moment from "moment";
import filters from "@/components/Filters";
import releaseTimeline from "@/components/ReleaseTimeline";
import { ViewModeEnum } from "@/constants/viewModeEnum.js";
export default {
  metaInfo: {
    htmlAttrs: {
      class: "has-navbar-fixed-top"
    }
  },
  data() {
    return {
      viewMode: ViewModeEnum.timeline
    };
  },
  components: {
    intervalSetter,
    oneday,
    tileCalendar,
    filters,
    releaseTimeline
  },
  methods: {
    changeViewMode() {
      switch (this.viewMode) {
        case ViewModeEnum.calendar:
          this.viewMode = ViewModeEnum.timeline;
          break;
        default:
          this.viewMode = ViewModeEnum.calendar;
      }
    }
  },
  computed: {
    viewModeButtonText() {
      switch (this.viewMode) {
        case ViewModeEnum.calendar:
          return ViewModeEnum.timeline;
        default:
          return ViewModeEnum.calendar;
      }
    },
    displayCalendar() {
      return this.viewMode == ViewModeEnum.calendar;
    },
    displayTimeline() {
      return this.viewMode == ViewModeEnum.timeline;
    }
  }
};
</script>
