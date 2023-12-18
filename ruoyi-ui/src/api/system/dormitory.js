import request from '@/utils/request'

// 查询dormitory列表
export function listDormitory(query) {
  return request({
    url: '/system/dormitory/list',
    method: 'get',
    params: query
  })
}

// 查询dormitory详细
export function getDormitory(id) {
  return request({
    url: '/system/dormitory/' + id,
    method: 'get'
  })
}

// 新增dormitory
export function addDormitory(data) {
  return request({
    url: '/system/dormitory',
    method: 'post',
    data: data
  })
}

// 修改dormitory
export function updateDormitory(data) {
  return request({
    url: '/system/dormitory',
    method: 'put',
    data: data
  })
}

// 删除dormitory
export function delDormitory(id) {
  return request({
    url: '/system/dormitory/' + id,
    method: 'delete'
  })
}
