import request from '@/utils/request'

// 查询keeper列表
export function listKeeper(query) {
  return request({
    url: '/system/keeper/list',
    method: 'get',
    params: query
  })
}

// 查询keeper详细
export function getKeeper(id) {
  return request({
    url: '/system/keeper/' + id,
    method: 'get'
  })
}

// 新增keeper
export function addKeeper(data) {
  return request({
    url: '/system/keeper',
    method: 'post',
    data: data
  })
}

// 修改keeper
export function updateKeeper(data) {
  return request({
    url: '/system/keeper',
    method: 'put',
    data: data
  })
}

// 删除keeper
export function delKeeper(id) {
  return request({
    url: '/system/keeper/' + id,
    method: 'delete'
  })
}
