<template>
  <a class="tile is-child" :href="release.shop_url" target="_blank">
    <figure class="image" style="width:184px; height:86px; margin:0">
      <img :src="release.primary_image" />
    </figure>
  </a>
</template>

<script>
export default {
  props: ["release"]
};
</script>
