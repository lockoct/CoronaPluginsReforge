package handler

import Main
import entities.Address
import entities.AddressBook
import i18n.color
import i18n.locale
import i18n.send
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import utils.mapper
import java.io.File
import java.nio.file.Paths

class LocationHandler(private val player: Player, private val dataDir: String = Main.plugin.dataFolder.path) {

    private val file = getUserAddressBookFile()
    private val book = getPlayerAddressBook() ?: AddressBook()
    private val invalidAddressNameMessage = "Invalid address name.".locale(player).color(ChatColor.RED)

    private fun getUserAddressBookFile(): File {
        return Paths.get(dataDir, "${player.name}.json").toFile()
    }

    fun getPlayerAddressBook(): AddressBook? {
        val file = getUserAddressBookFile()
        if (!file.exists()) {
            return null
        }
        return mapper.readValue(file, AddressBook::class.java)
    }

    fun savePlayerLocation(name: String): Boolean {
        if (!validateAddressName(name)) {
            invalidAddressNameMessage.send(player)
            return false
        }

        val location = player.location
        val address = Address(name, location.x, location.y, location.z, location.world!!.name)

        if (book.address.size >= book.limit) {
            "Can't save address due to maximum limit.".locale(player).color(ChatColor.RED).send(player)
            return false
        }

        if (book.address.find { item -> item.name == name } != null) {
            "Duplicated address name.".locale(player).color(ChatColor.RED).send(player)
            return false
        }

        book.address.add(address)
        mapper.writeValue(file, book)
        return true
    }

    fun renamePlayerLocation(oldName: String, newName: String) {
        if (!validateAddressName(newName)) {
            invalidAddressNameMessage.send(player)
            return
        }
        val location = book.address.find { item -> item.name == oldName }

        if (location == null) {
            "Can't find the location with name ".locale(player).color(ChatColor.RED).send(player)
            return
        }
        location.name = newName
        mapper.writeValue(file, book)
    }

    fun validateAddressName(name: String): Boolean {
        if (name.isBlank()) {
            return false
        } else if (name.length > 20) {
            return false
        }

        return true
    }
}