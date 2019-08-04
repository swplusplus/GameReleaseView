<template>
  <div class="content">
    <h5 class="title-is-5">{{date | formatDate}}</h5>
    <releaseTile v-for="(release, index) of releases" :key="index" :release="release"></releaseTile>
  </div>
</template>

<script>
import moment from "moment";
import releaseTile from "@/components/ReleaseTile";
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
  },
  components: {
    releaseTile
  }
};
</script>

