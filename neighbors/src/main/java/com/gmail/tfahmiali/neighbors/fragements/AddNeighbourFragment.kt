package com.gmail.tfahmiali.neighbors.fragements

import android.net.Uri
import android.os.Bundle
import android.text.Editable
//import android.text.TextUtils
import android.text.TextWatcher
//import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
//import android.widget.Button
//import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
//import com.gmail.tfahmiali.neighbors.MainActivity
import com.gmail.tfahmiali.neighbors.NavigationListener
import com.gmail.tfahmiali.neighbors.R
import com.gmail.tfahmiali.neighbors.data.NeighborRepository
import com.gmail.tfahmiali.neighbors.databinding.AddNeighborBinding
import com.gmail.tfahmiali.neighbors.models.Neighbor

class AddNeighbourFragment : Fragment(), TextWatcher {
    private lateinit var binding: AddNeighborBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        binding = AddNeighborBinding.inflate(inflater, container, false)
        val view = binding.root
//        val view = inflater.inflate(R.layout.add_neighbor, container, false)
//        val buttonenregistre = view.findViewById<Button>(R.id.button_enregistrer)
//        buttonenregistre.setOnClickListener {
//            sendform(view)
//        }
        (activity as? NavigationListener)?.updateTitle(R.string.add_name)
        binding.buttonEnregistrer.setOnClickListener {
            NeighborRepository.getInstance().addNeighbours(sendform())
            (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())

        }
        with(binding) {
            textNom.addTextChangedListener(this@AddNeighbourFragment)
            textAdresse.addTextChangedListener(this@AddNeighbourFragment)
            textTelephone.addTextChangedListener(this@AddNeighbourFragment)
            textWebste.addTextChangedListener(this@AddNeighbourFragment)
            textAproposedemoi.addTextChangedListener(this@AddNeighbourFragment)
            itemImgage.addTextChangedListener(this@AddNeighbourFragment)

        }

        return view
    }

    private fun sendform(): Neighbor {
        with(binding) {
            val neighborname = textNom.text.toString()
            val neighboravatarUrl = itemImgage.text.toString()
            val neighboraddress = textAdresse.text.toString()
            val neighborphone = textTelephone.text.toString()
            val aboutMe = textAproposedemoi.text.toString()
            val webSite = textWebste.text.toString()

            val neigbour = Neighbor(
                NeighborRepository.getInstance().getIdNeigbours(),
                neighborname,
                neighboravatarUrl,
                neighboraddress,
                neighborphone,
                aboutMe,
                false,
                webSite
            )
            context?.let {
                Glide.with(it).load(Uri.parse(neighboravatarUrl)).into(binding.imageView)
            }

            return neigbour
        }


//        val neighborname = view.findViewById<TextView>(R.id.text_nom).text.toString()
//        val neighboravatarUrl = view.findViewById<TextView>(R.id.item_imgage).text.toString()
//        val neighboraddress = view.findViewById<TextView>(R.id.text_adresse).text.toString()
//        val neighborphone = view.findViewById<TextView>(R.id.text_telephone).text.toString()
//        val aboutMe = view.findViewById<TextView>(R.id.text_aproposedemoi).text.toString()
//        val webSite = view.findViewById<TextView>(R.id.text_Webste).text.toString()

    }

    private fun checkButton() {
        with(binding) {
            val textNomNotEmpty: Boolean = !textNom.text.isNullOrEmpty()
            val textAdressNotEmpty: Boolean = !textAdresse.text.isNullOrEmpty()
            val textTelephoneNotEmpty: Boolean = !textTelephone.text.isNullOrEmpty()
            val textWebsteNotEmpty: Boolean = !textWebste.text.isNullOrEmpty()
            val textAproposdemoiNotEmpty: Boolean = !textAproposedemoi.text.isNullOrEmpty()
            val itemImgageNotEmpty: Boolean = !itemImgage.text.isNullOrEmpty()


            val textTelephoneCheck: Boolean = checkPhoneNumber(textTelephone.text)
            if (!textTelephoneCheck && textTelephoneNotEmpty) {
                textTelephone.error = "Format 07/06 XX XX XX XX"
            }

            val itemImgageUrlCheck: Boolean = checkUrl(itemImgage.text)
            if (!itemImgageUrlCheck && itemImgageNotEmpty) {
                itemImgage.error = "image URL Non Valide"
            }

            val textWebsteUrlCheck: Boolean = checkUrl(textWebste.text)
            if (!textWebsteUrlCheck && textWebsteNotEmpty) {
                textWebste.error = "URL Non Valide"
            }

            val textAproposdemoi: Boolean = checkAboutme(textAproposedemoi.text)
            if (!textAproposdemoi){
                textAproposedemoi.error = "Maximum 30 characteres"
            }

            buttonEnregistrer.isEnabled =
                textNomNotEmpty &&
                        textAdressNotEmpty &&
                        textTelephoneNotEmpty &&
                        textWebsteNotEmpty &&
                        textAproposdemoiNotEmpty &&
                        itemImgageNotEmpty &&
                        textTelephoneCheck &&
                        itemImgageUrlCheck &&
                        textWebsteUrlCheck
        }
    }


    private fun checkPhoneNumber(target: CharSequence?): Boolean {
        return (
                (
                        (target.toString()).startsWith("07") ||
                                (target.toString()).startsWith("06")
                        ) &&
                        target.toString().length == 10
                )
    }

    private fun checkAboutme(target: CharSequence?): Boolean {
        return target.toString().length < 30

    }

    private fun checkUrl(target: CharSequence?): Boolean {
        return URLUtil.isValidUrl(target.toString())
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        checkButton()
    }

    override fun afterTextChanged(s: Editable?) {
        context?.let { Glide.with(it).load(sendform().avatarUrl.toString()).into(binding.imageView) }
    }


}