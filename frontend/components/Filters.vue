<template>
  <div>
    <table class="table">
      <thead>
        <tr>
          <th>Category</th>
          <th>Value(number of releases)</th>
        </tr>
      </thead>
      <tfoot>
        <tr>
          <th>Category</th>
          <th>Value(number of releases)</th>
        </tr>
      </tfoot>
      <tbody>
        <filter_row v-for="(item) in filter_attrs" :key="item.category" :item="item">
        </filter_row>
      </tbody>
    </table>
  </div>
</template>

<script>
import moment from "moment";
import filter_row from "@/components/FilterRow";
export default {
  computed: {
    filter_attrs() {
      var filterAttrs = this.$store.getters.getAttrFilters;
      return filterAttrs;
    }
  },
  components:{
    filter_row
  },
  props: {}
};
</script>
