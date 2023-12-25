import request from '@/utils/request'

// 查询assignment列表
export function listAssignment(query) {
  return request({
    url: '/system/assignment/list',
    method: 'get',
    params: query
  })
}

// 查询assignment详细
export function getAssignment(id) {
  return request({
    url: '/system/assignment/' + id,
    method: 'get'
  })
}

// 新增assignment
export function addAssignment(data) {
  return request({
    url: '/system/assignment',
    method: 'post',
    data: data
  })
}

// 修改assignment
export function updateAssignment(data) {
  return request({
    url: '/system/assignment',
    method: 'put',
    data: data
  })
}

// 删除assignment
export function delAssignment(id) {
  return request({
    url: '/system/assignment/' + id,
    method: 'delete'
  })
}
