<template>
  <section class="section">
    <div class="columns">
      <div class="column box has-background-primary">Montag</div>
      <div class="column box has-background-primary">Dienstag</div>
      <div class="column box has-background-primary">Mittwoch</div>
      <div class="column box has-background-primary">Donnerstag</div>
      <div class="column box has-background-primary">Freitag</div>
      <div class="column box has-background-primary">Samstag</div>
      <div class="column box has-background-primary">Sonntag</div>
    </div>
    <div class="columns" v-for="i in tileDescriptors.length/7" :key="i">
      <div
        class="column box"
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
        .date(i);
    }
  },
  computed: {
    tileDescriptors() {
      var tiles = [];
      var m = this.month;
      m.date(1);

      // init with padding, if month does not start on monday
      // 1=monday, 7=saturday
      for (var i = 1; i < m.isoWeekday(); ++i) {
        tiles.push(null);
      }

      for (var i = 1; i < m.daysInMonth() + 1; ++i) {
        tiles.push(i);
      }

      console.info(tiles);
      return tiles;
    }
  }
};
</script>

