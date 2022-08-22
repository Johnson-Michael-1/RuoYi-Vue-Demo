import request from '@/utils/request'

// 查询岗位列表
export function listStudent(query) {
  return request({
    url: '/system/websocketstudent/list',
    method: 'get',
    params: query
  })
}

// 查询学生详细
export function getStudent(studentId) {
  return request({
    url: '/system/websocketstudent/' + studentId,
    method: 'get'
  })
}

// 新增学生
export function addStudent(data) {
  return request({
    url: '/system/websocketstudent',
    method: 'post',
    data: data
  })
}

// 修改学生
export function updateStudent(data) {
  return request({
    url: '/system/websocketstudent',
    method: 'put',
    data: data
  })
}

// 删除学生
export function delStudent(studentId) {
  return request({
    url: '/system/websocketstudent/' + studentId,
    method: 'delete'
  })
}
