import service from "./request";

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