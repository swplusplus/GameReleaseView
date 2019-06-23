<template>
  <div>
    <section class="hero">
      <section class="section" v-for="index in 31" :key="index">
        <oneday :date="computeDate(index-1)"/>
      </section>
    </section>
  </div>
</template>

<script>
import oneday from "@/components/OneDay";
import moment from "moment";
export default {
  metaInfo: {
    htmlAttrs: {
      class: "has-navbar-fixed-top"
    }
  },
  components: {
    oneday
  },
  methods: {
    computeDate(daysFromToday) {
      return moment()
        .utc()
        .startOf("day")
        .add(daysFromToday, "days");
    }
  }
};
</script>
