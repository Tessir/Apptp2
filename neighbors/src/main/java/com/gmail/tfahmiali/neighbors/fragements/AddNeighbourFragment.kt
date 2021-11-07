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


            val usecheckName = checkEmpty(textNom.text)
            val usecheckAdress= checkEmpty(textAdresse.text)
            val usecheckPhoneNumber = checkPhoneNumber(textTelephone.text)
            val usecheckAboutme = checkAboutme(textAproposedemoi.text)
            val usecheckImageUrl= checkUrl(itemImgage.text)
            val usecheckWebsiteUrl= checkUrl(textWebste.text)

            if (!usecheckName){
                textNom.error="Champs Obligatoire"
            }
            if (!usecheckAdress){
                textAdresse.error="Champs Obligatoire"
            }

            if (!usecheckPhoneNumber) {
                textTelephone.error = "Format 07/06 XX XX XX XX"
            }

            if (!usecheckImageUrl) {
                itemImgage.error = "Format image No Valid"
            }

            if (!usecheckWebsiteUrl) {
                textWebste.error = "Format URL No Valid"
            }

            if (!usecheckAboutme ) {
                textAproposedemoi.error = "Maximum 30 characters"
            }

            buttonEnregistrer.isEnabled =
                        usecheckImageUrl &&
                         usecheckName &&
                        usecheckPhoneNumber &&
                        usecheckWebsiteUrl &&
                        usecheckAdress &&
                        usecheckAboutme

        }
    }

    private fun checkEmpty(target: CharSequence?): Boolean {
        return target.toString().isNotEmpty()

    }
    private fun checkPhoneNumber(target: CharSequence?) : Boolean{

        return (
                ((target.toString()).startsWith("07") ||
                (target.toString()).startsWith("06")
                ) &&
                target.toString().length == 10
                && target.toString().isNotEmpty()
                )
    }

    private fun checkAboutme(target: CharSequence?): Boolean {
        return (target.toString().length < 30 && target.toString().isNotEmpty())

    }

    private fun checkUrl(target: CharSequence?): Boolean {
        return (URLUtil.isValidUrl(target.toString()) && target.toString().isNotEmpty())
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        checkButton()
        context?.let {
            Glide.with(it).load(sendform().avatarUrl).into(binding.imageView)
        }

    }


}