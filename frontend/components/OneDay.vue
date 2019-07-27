<template>
  <div class="content">
    <h5 class="title-is-5">{{date | formatDate}}</h5>
    <p class="content" v-for="(release, index) of releases" :key="index">{{release.name}}</p>
  </div>
</template>

<script>
import moment from "moment";
export default {
  computed: {
    releases() {
      return this.$store.getters.getVisibleReleaseByDate(this.date);
    }
  },
  props: {
    date: {
      type: moment,
      default: moment()
    }
  }
};
</script>

