import { defineStore } from 'pinia'
import { Tree } from '../type'

export const catalogTreeStore = defineStore({
    id: 'catalogTree',
    state: () => ({
        data: [] as Tree[],
    }),
    actions: {
        changeName(data: Tree[]) {
            this.data = data
        }
    }
})

