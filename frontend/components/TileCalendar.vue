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
        :class="{'has-background-grey': !isValidTile(index(i, ii)), 'has-background-light': isValidTile(index(i, ii))}"
        :key="ii"
      >
        <oneday v-if="isValidTile(index(i, ii))" :date="tileDate(tileDescriptors[index(i, ii)])" />
      </div>
    </div>
  </section>
</template>

<script>
import moment from "moment";
import oneday from "@/components/OneDay";
export default {
  props: {
  },
  components: {
    oneday
  },
  methods: {
    isValidTile(i) {
      return i < this.tileDescriptors.length && this.tileDescriptors[i] != null;
    },
    tileDate(i) {
      return moment(this.dateStart).add(i, "d")
        .startOf("day");
    },
    index(i, ii) {
      return (ii-1+(i-1)*7)
    }
  },
  computed: {
    dateStart() {
        return moment(this.$store.getters.getIntervalStart);
    },
    dateEnd() {
      return moment(this.$store.getters.getIntervalEnd);
    },
    tileDescriptors() {
      var tiles = [];
      if (!this.dateEnd || !this.dateStart){
        console.error("interval not set");
        return tiles;
      }
      if (this.dateStart.isAfter(this.dateEnd)) {
        console.error("end must be after start");
        return tiles;
      }

      var m = new moment(this.dateStart);

      // init with padding, if month does not start on monday
      // 1=monday, 7=saturday
      for (var i = 1; i < m.isoWeekday(); ++i) {
        tiles.push(null);
      }

      var i = 0;
      while (!m.isAfter(this.dateEnd)){
        m.add(1, "d");
        tiles.push(i);
        ++i;
      }

      // for (var i = 1; i < this.dateEnd + 1; ++i) {
      // }

      var padding = tiles.length % 7;
      for (var i = 0; i < padding; ++i) {
        tiles.push(null);
      }

      return tiles;
    }
  }
};
</script>

