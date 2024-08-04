<template>
<div class="note-title">{{noteData.title}}</div>

<div class="button-group">
  <el-button
      key="info"
      type="info"
      text
      size="large"
      v-on:click="toNewNote"
    >
    新建
  </el-button>
  
  <el-button
      key="info"
      type="info"
      text
      size="large"
      v-on:click="toEditor"
    >
    编辑
  </el-button>
</div>

<div id="main_content" v-html="noteData.content"></div>

</template>

<script setup lang="ts">

import {onMounted, onUpdated, ref, Ref} from 'vue';
import {getNoteById, Response} from '../http/api'
import Vditor from 'vditor'
import 'vditor/dist/index.css'
import router from '../router/router';

const props = defineProps({
  noteId: String,
})

interface Note {
    id: string
    title: string
    content: string
}

const noteData: Ref<Note> = ref({
  id: '-1',
  title: '',
  content: '',
})

const options: IPreviewOptions = {
    mode: "light", 
    cdn: ' https://unpkg.com/vditor@3.10.4',
  
  }

const toHtml = (markdown: string): void => {
  
  Vditor.md2html(markdown, options).then(html => {
    noteData.value.content = html
  })
 
}

onMounted(() => {
  getNoteById(props.noteId as string).then(
      (resp) => {
        let response: Response = resp.data

        if (response.data === null) {
          alert("can not get note msg with noteId: " + props.noteId)
          throw "can not get note msg with noteId: " + props.noteId
        }
        noteData.value.id = response.data.id
        noteData.value.title = response.data.title
        toHtml(response.data.content)
      }
  )
})

onUpdated(() => {
  getNoteById(props.noteId as string).then(
      (resp) => {
        let response: Response = resp.data
        if (response.data === null) {
          alert("can not get note msg with noteId: " + props.noteId)
          throw "can not get note msg with noteId: " + props.noteId
        }
        noteData.value.id = response.data.id
        noteData.value.title = response.data.title
        toHtml(response.data.content)
      }
  )
})


const toEditor = () => {

  router.push("/edit/" + noteData.value.id)

  //window.location.href='#/edit/' + noteData.value.id
}

const toNewNote = () => {
  router.push({
    name:"create",
    state:{
      parentId: props.noteId
    }
    })


  //window.location.href='#/edit/' + noteData.value.id
}


// watch(
//     () => props.noteId,
//     (noteId) => {
//         console.log("props of noteContent has changed!")
//         getNoteById(noteId as string).then(
//             (note) => {
//               if (note.data === null) {
//                 alert("can not get note msg with noteId: " + noteId)
//                 throw "can not get note msg with noteId: " + noteId
//               }
//               noteData.value.id = note.data.id
//               noteData.value.title = note.data.title
//               toHtml(note.data.content)
//             })
//     }
// );

</script>

<style scoped>
.note-title {
  color: #000000;
  font-size: 32px;
}

.button-group {
  margin-top: 10px;
}

a:link {
  color: #000;
  text-decoration: none;
}


</style>
