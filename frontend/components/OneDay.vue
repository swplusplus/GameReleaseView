<template>
  <div class="card">
    <header class="card-header">
      <p class="card-header-title">{{date | formatDate}}</p>
    </header>
    <div class="card-content">
      <div class="content">
        <p
          class="content"
          v-for="(release, index) of releases"
          :key="index"
        >{{release.name}} {{release.dateFrom | formatDate}} - {{release.dateTo | formatDate}} {{release.originalDate}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
export default {
  computed: {
    releases() {
      return this.$store.getters.getReleaseByDate(this.date);
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

