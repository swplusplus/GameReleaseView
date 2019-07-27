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
              <filter_setter
                v-for="(value, index) in item.values"
                :key="index"
                :name="value.category_value"
                :count="value.games.length"
              ></filter_setter>
            </p>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import moment from "moment";
import filter_setter from "@/components/FilterSetter";
export default {
  computed: {
    filter_attrs() {
      var filterAttrs = this.$store.getters.getAttrFilters;
      return filterAttrs;
    }
  },
  components:{
    filter_setter
  },
  props: {}
};
</script>
