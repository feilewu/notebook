import service from "./request";

export interface Note {
  id: string,
  title: string,
  content: string,
  authorId: number,
  editorId: number,
  createTime: number,
  modifyTime: number,
  parentId: number,
}

export interface Response {
  code: number,
  data: any
}


export function getCatalogNode(key: String){
    return service({
      url: '/api/catalog/' + key,
      method: 'get'
    })
}


export function getNoteById(key: String){
  return service({
    url: '/api/note/' + key,
    method: 'get'
  })
}

export function createNote(note: Note){
  return service({
    url: '/api/note/',
    data: note,
    method: 'post'
  })
}


export function updateNoteById(note: Note){
  return service({
    url: '/api/note/',
    data: note,
    method: 'put'
  })
}

export function deleteNoteById(key: String){
  return service({
    url: '/api/note/' + key,
    method: 'delete'
  })
}



