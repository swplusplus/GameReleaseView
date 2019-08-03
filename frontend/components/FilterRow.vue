<template>
    <tr>
        <th><p>{{item.category.replace('_',' ')}}</p><button class="button" @click="SetCollapsed">{{collapsed?"+":"-"}}</button></th>
        <td>
        <p class="buttons" v-show="!collapsed">
            <filter_setter
            v-for="(value, index) in item.values"
            :key="index"
            :name="value.category_value"
            :count="value.games.length"
            @state-changed="FilterStateChanged($event)"
            ></filter_setter>
        </p>
        </td>
    </tr>
</template>

<script>
import filter_setter from "@/components/FilterSetter";
import {StateEnum} from "@/constants/stateEnum.js";
export default {
    data() {
        return {
            collapsed: this.item.values.length > 30,
            filterStates: this.item.values.map(filt => {return {state: StateEnum.default, filter: filt};})
        }
    },
    props:{
        item: null
    },
    components: {
        filter_setter
    },
    methods: {
        SetCollapsed() {
            this.collapsed = !this.collapsed;
        },
        FilterStateChanged(event) {
            this.filterStates.find(element => {return element.filter.category_value == event.name;}).state = event.state;
            var includedFilters = [];
            var excludedFilters = [];
            //var hasIncluded = -1 != this.filterStates.findIndex(state => {return state.state == StateEnum.included;});
            this.filterStates.forEach(element => {
                if (element.state == StateEnum.included) {
                    includedFilters.push(element.filter);
                } else if (element.state == StateEnum.excluded) {
                    excludedFilters.push(element.filter);
                }
            });
            this.$store.dispatch('setCategoryFilters', {category: this.item.category, excluded: excludedFilters, included: includedFilters});
        }
    }
}
</script>
