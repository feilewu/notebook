<template>


    <ol>
        <li v-for="space in dataRef.data">
        <div v-bind:spaceId=space.id @click="clickEvent(space.id)">{{ space.name }}</div>  
        </li>
    </ol>

</template>

<script lang="ts" setup>

import Space from 'element-plus/es/components/space/src/space.mjs';
import {onMounted, ref} from 'vue';
import router from '../router/router';


interface Space {
    name: string,
    id: number
}


const dataRef = ref({
    data: [] as Space[]
})


onMounted(() => {

    dataRef.value.data = [
        {
            name: "space 1",
            id: 1
        },
        {
            name: "space 2",
            id: 2
        },
    ]
}) 

const clickEvent = (spaceId: any) => {
    router.push("/space/" + spaceId)
}


</script>
