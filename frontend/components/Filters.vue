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
        <tr v-for="(item) in filter_attrs" :key="item.category">
          <th>{{item.category.replace('_',' ')}}</th>
          <td>
            <p class="buttons">
              <button
                class="button is-small"
                v-for="(value, index) in item.values"
                :key="index"
              >{{value.category_value}} ({{value.games.length}})</button>
            </p>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import moment from "moment";
export default {
  computed: {
    filter_attrs() {
      var filterAttrs = this.$store.getters.getAttrFilters;
      return filterAttrs;
    }
  },
  props: {}
};
</script>
