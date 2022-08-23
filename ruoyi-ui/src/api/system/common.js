import request from '@/utils/request'
import { parseStrEmpty } from '@/utils/ruoyi';

/**
 * 文件上传
 * @param data {Object} formData对象 "file"
 */
// 上传图片文件
export function uploadImgFile(data) {
  return request({
    url: '/common/uploadImgFile' ,
    method: 'post',
    data: data
  })
}

// 删除图片文件
export function delImgFile(data) {
  return request({
    url: '/common/delImgFile' ,
    method: 'post',
    data: data
  })
}


