package phamtuan.doctoralarm.controllers.group.listener

import phamtuan.doctoralarm.entity.Group

/**
 * Created by P.Tuan on 11/28/2017.
 */
interface GroupListIteracter {
    fun OnLoadGroupSuccess(listGroup:ArrayList<Group>)
}