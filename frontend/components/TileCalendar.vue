<template>
  <section class="section">
    <div class="columns">
      <div class="column box has-background-primary is-vcentered">Montag</div>
      <div class="column box has-background-primary is-vcentered">Dienstag</div>
      <div class="column box has-background-primary is-vcentered">Mittwoch</div>
      <div class="column box has-background-primary is-vcentered">Donnerstag</div>
      <div class="column box has-background-primary is-vcentered">Freitag</div>
      <div class="column box has-background-primary is-vcentered">Samstag</div>
      <div class="column box has-background-primary is-vcentered">Sonntag</div>
    </div>
    <div class="columns" v-for="i in Math.ceil(tileDescriptors.length/7)" :key="i">
      <div
        class="column box is-vcentered"
        v-for="ii in 7"
        :class="{'has-background-grey': !isValidTile(ii-1+(i-1)*7), 'has-background-light': isValidTile(ii-1+(i-1)*7)}"
        :key="ii"
      >
        <oneday v-if="isValidTile(ii-1+(i-1)*7)" :date="tileDate(tileDescriptors[ii-1+(i-1)*7])" />
      </div>
    </div>
  </section>
</template>

<script>
import moment from "moment";
import oneday from "@/components/OneDay";
export default {
  props: {
    month: {
      type: moment,
      required: true
    }
  },
  components: {
    oneday
  },
  methods: {
    isValidTile(i) {
      return i < this.tileDescriptors.length && this.tileDescriptors[i] != null;
    },
    tileDate(i) {
      return moment()
        .utc()
        .date(i)
        .startOf("day");
    }
  },
  computed: {
    tileDescriptors() {
      var tiles = [];
      var m = this.month.date(1).startOf("day");

      // init with padding, if month does not start on monday
      // 1=monday, 7=saturday
      for (var i = 1; i < m.isoWeekday(); ++i) {
        tiles.push(null);
      }

      for (var i = 1; i < m.daysInMonth() + 1; ++i) {
        tiles.push(i);
      }

      var padding = tiles.length % 7;
      for (var i = 0; i < padding; ++i) {
        tiles.push(null);
      }

      return tiles;
    }
  }
};
</script>

