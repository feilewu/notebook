<template>
<div class="note-title">{{noteData.title}}</div>

<div class="button-group">
  <el-button
      key="info"
      type="info"
      text
      size="large"
    >
    新建
  </el-button>
  
  <el-button
      key="info"
      type="info"
      text
      size="large"
    >
    <a :href="'#/edit/' + noteData.id">编辑</a>
  </el-button>
</div>

<div v-html="noteData.content"></div>

</template>

<script setup lang="ts">

import {watch, ref, Ref} from 'vue';
import {getNoteById} from '../http/api'
import Vditor from 'vditor'
import 'vditor/dist/index.css'

const props = defineProps({
  noteId: String,
})

interface Note {
    id: string
    title: string
    content: string
}

const noteData: Ref<Note> = ref({
  id: '',
  title: '',
  content: '',
})

const toHtml = (markdown: string): void => {
  const options: IPreviewOptions = {
    mode: "light", 
    cdn: ' https://unpkg.com/vditor@3.10.4'
  }
  Vditor.md2html(markdown, options).then(html => {
    noteData.value.content = html
  })
}

watch(
    () => props.noteId,
    (noteId) => {
        console.log("props of noteContent has changed!")
        getNoteById(noteId as string).then(
            (note) => {
              if (note.data === null) {
                alert("can not get note msg with noteId: " + noteId)
                throw "can not get note msg with noteId: " + noteId
              }
              noteData.value.id = note.data.id
              noteData.value.title = note.data.title
              toHtml(note.data.content)
            })
    }
);

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
