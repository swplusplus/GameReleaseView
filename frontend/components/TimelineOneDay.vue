<template>
  <div class="content">
    <h5 class="heading">{{date | formatDate}}</h5>
    <div class="tile is-ancestor">
      <div class="tile is-parent" style="flex-wrap: wrap;">
        <releaseTile v-for="(release, index) of releases" :key="index" :release="release"></releaseTile>
      </div>
    </div>
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

