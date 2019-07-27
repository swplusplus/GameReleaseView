<template>
    <button
    class="button is-small"
    >{{name}} ({{count}})</button>
</template>

<script>
export default {
    props: {
        name: "",
        count: 0
    }
}
</script>

