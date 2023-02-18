package org.iris.wiki.utils


import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.message.data.*
import org.iris.wiki.config.MESSAGE_PARSE_ERROR
import org.iris.wiki.config.SEARCH_URL
import org.iris.wiki.data.*
import java.lang.Exception
import java.net.URLEncoder
import javax.imageio.IIOException


object MessageBuildUtils {

    suspend fun build(sender: Member, data: Data?, commandList: List<String>) : Message {

        // 只有解析失败会返回null，此时存在页面但不能解析
        if (data == null) {
            return PlainText(MESSAGE_PARSE_ERROR + SEARCH_URL + URLEncoder.encode(commandList[1]))
        }


        // 生成message
        return try {
            if (data.at) {
                At(sender).plus(data.toMessage(sender)).plus(data.extra_msg.build())
            } else {
                data.toMessage(sender).plus(data.extra_msg.build())
            }
        } catch (e: IIOException) {
            e.printStackTrace()
            PlainText("图片读取失败，可能是数据文件不是最新版本喵\n如果是大建报错请使用命令 /wkcf updateShipIcon 更新舰娘头像数据喵\n$e")
        } catch (e: Exception) {
            e.printStackTrace()
            PlainText("出错了喵 QAQ\n$e")
        }
    }

}