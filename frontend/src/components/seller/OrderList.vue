<template>
<div>
    <h1>test3</h1>
</div>
</template>

<script>
export default {
    data: function () {
        return {
            counter: 0
        };
    },
    destroyed() {
        console.log('Destroyed')
    }
}
</script>

<style>

</style>
