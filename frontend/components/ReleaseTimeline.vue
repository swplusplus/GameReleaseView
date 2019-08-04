<template>
  <section class="section">
    <div class="timeline is-centered">
      <!-- <header class="timeline-header">
        <span class="tag is-medium is-primary">{{dateStart}}</span>
      </header>-->
      <div class="timeline-item is-primary" v-for="(date,index) in dates" :key="index">
        <div class="timeline-marker is-primary is-image is-32x32">
          <p style="text-align: center; padding-top: 2px;">{{numberOfReleases(date)}}</p>
        </div>
        <div class="timeline-content">
          <oneday :date="date" />
        </div>
      </div>
      <!-- <div class="timeline-item is-warning">
        <div class="timeline-marker is-warning is-image is-32x32">
          <img src="http://bulma.io/images/placeholders/32x32.png" />
        </div>
        <div class="timeline-content">
          <p class="heading">February 2016</p>
          <p>Timeline content - Can include any HTML element</p>
        </div>
      </div>
      <header class="timeline-header">
        <span class="tag is-primary">2017</span>
      </header>
      <div class="timeline-item is-danger">
        <div class="timeline-marker is-danger is-icon">
          <i class="fa fa-flag"></i>
        </div>
        <div class="timeline-content">
          <p class="heading">March 2017</p>
          <p>Timeline content - Can include any HTML element</p>
        </div>
      </div>-->
      <!-- <header class="timeline-header">
        <span class="tag is-medium is-primary">{{dateEnd}}</span>
      </header>-->
    </div>
  </section>
</template>

<script>
import moment from "moment";
import oneday from "@/components/TimelineOneDay";
export default {
  computed: {
    dateStart() {
      return moment(this.$store.getters.getIntervalStart);
    },
    dateEnd() {
      return moment(this.$store.getters.getIntervalEnd);
    },
    dates() {
      var dates = [];
      var date = moment(this.dateStart);
      while (date.isSameOrBefore(this.dateEnd)) {
        if (this.numberOfReleases(date) > 0) {
          dates.push(moment(date));
        }
        date.add(1, "days");
      }
      return dates;
    }
  },
  components: {
    oneday
  },
  methods: {
    numberOfReleases(date) {
      return this.$store.getters.getVisibleReleaseByDate(date).length;
    }
  }
};
</script>

