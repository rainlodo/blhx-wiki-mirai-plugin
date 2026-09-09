package org.iris.wiki.utils

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor
import java.io.File
import java.util.LinkedHashMap

object ConfigYamlUtils {

    private val loaderOptions = LoaderOptions()
    private val loadYaml = Yaml(SafeConstructor(loaderOptions))
    private val dumpYaml = Yaml(
        DumperOptions().apply {
            defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
            isPrettyFlow = true
            indent = 2
            indicatorIndent = 1
        }
    )

    fun loadMap(file: File): Map<String, Any?> {
        if (!file.exists()) {
            return emptyMap()
        }
        return file.reader(Charsets.UTF_8).use { reader ->
            @Suppress("UNCHECKED_CAST")
            loadYaml.load<Any?>(reader) as? Map<String, Any?> ?: emptyMap()
        }
    }

    fun saveMap(file: File, values: Map<String, Any?>) {
        file.parentFile?.mkdirs()
        file.writeText(dumpYaml.dump(values), Charsets.UTF_8)
    }

    fun readString(map: Map<String, Any?>, key: String, defaultValue: String): String {
        return map[key]?.toString() ?: defaultValue
    }

    fun readBoolean(map: Map<String, Any?>, key: String, defaultValue: Boolean): Boolean {
        return when (val value = map[key]) {
            is Boolean -> value
            is String -> value.equals("true", ignoreCase = true)
            else -> defaultValue
        }
    }

    fun readStringArray(map: Map<String, Any?>, key: String, defaultValue: Array<String>): Array<String> {
        val value = map[key] ?: return defaultValue.copyOf()
        return when (value) {
            is List<*> -> value.mapNotNull { it?.toString() }.toTypedArray()
            is Array<*> -> value.mapNotNull { it?.toString() }.toTypedArray()
            is String -> arrayOf(value)
            else -> defaultValue.copyOf()
        }
    }

    fun readStringList(map: Map<String, Any?>, key: String, defaultValue: MutableList<String>): MutableList<String> {
        val value = map[key] ?: return defaultValue.toMutableList()
        return when (value) {
            is List<*> -> value.mapNotNull { it?.toString() }.toMutableList()
            is Array<*> -> value.mapNotNull { it?.toString() }.toMutableList()
            is String -> mutableListOf(value)
            else -> defaultValue.toMutableList()
        }
    }

    fun readStringMap(map: Map<String, Any?>, key: String, defaultValue: MutableMap<String, String> = linkedMapOf()): MutableMap<String, String> {
        val value = map[key] ?: return LinkedHashMap(defaultValue)
        if (value !is Map<*, *>) {
            return LinkedHashMap(defaultValue)
        }
        return LinkedHashMap<String, String>().apply {
            value.forEach { (mapKey, mapValue) ->
                if (mapKey != null && mapValue != null) {
                    this[mapKey.toString()] = mapValue.toString()
                }
            }
        }
    }
}
