import request from '@/utils/request'

// Student
export function listStudent(query) {
  return request({
    url: '/system/sdormitory/list',
    method: 'get',
    params: query
  })
}

// 查询dormitory详细
export function getStudent(id) {
  return request({
    url: '/system/sdormitory/' + id,
    method: 'get'
  })
}

// 新增dormitory
export function addStudent(data) {
  return request({
    url: '/system/sdormitory',
    method: 'post',
    data: data
  })
}

// 修改dormitory
export function updateStudent(data) {
  return request({
    url: '/system/sdormitory',
    method: 'put',
    data: data
  })
}

// 删除dormitory
export function delStudent(id) {
  return request({
    url: '/system/sdormitory/' + id,
    method: 'delete'
  })
}
