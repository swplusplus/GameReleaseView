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
            ></filter_setter>
        </p>
        </td>
    </tr>
</template>

<script>
import filter_setter from "@/components/FilterSetter";
export default {
    data() {
        return {
            collapsed: this.item.values.length > 30
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
        }
    }
}
</script>
