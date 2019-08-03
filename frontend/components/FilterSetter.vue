<template>
    <button
    class="button is-small"
    :class="{
        'is-primary': included,
        'is-light': excluded,
        'is-white': !included && !excluded,
        }"
    @click="ToggleState"
    >
        <span v-show="true ||included || excluded" class="icon is-small">
            <i class="fas fa-check" :class="{'fa-check': included, 'fa-times': excluded}"></i>
        </span>
        <span>{{name}} ({{count}})</span>
    </button>
</template>

<script>
import {StateEnum} from "@/constants/stateEnum.js";
export default {
    data() {
        return {
            state: StateEnum.default,
            overallMode: StateEnum.default
        }
    },
    props: {
        name: "",
        count: 0
    },
    methods: {
        ToggleState() {
            switch (this.state) {
                case StateEnum.default:
                    this.state = StateEnum.included;
                    break;
                case StateEnum.included:
                    this.state = StateEnum.excluded;
                    break;
                default:
                    this.state = StateEnum.default;
                    break;
            }
            this.$emit('state-changed', {name: this.name, state: this.state});
        }
    },
    computed: {
        included() {
            return this.state == StateEnum.included;
        },
        excluded() {
            return this.state == StateEnum.excluded;
        }
    }
}
</script>

