package com.codingwithset.sotech

import android.cims.chams.com.osun.dashbord.util.DepthPageTransformer
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.codingwithset.sotech.images.fragment.DashBoardAdapter
import com.codingwithset.sotech.images.fragment.ExitAppDialogFragment
import com.codingwithset.sotech.utils.readJsonAsset
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var value: String? = null
    var isTracking = false
    var model: Model? = null
    private var count = 0
    val mutableScannerTracker = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tool_bar)


        scan_barcode.setOnClickListener {
            scanBarcode()
        }



        try {
            scan_image.visibility = View.GONE
            layout1.visibility = View.VISIBLE
            layout2.visibility = View.VISIBLE

            value = intent.getStringExtra("value")


            if (value!!.isNotEmpty()) {
                val result = value
                val data = getDetails(result)
                if (data != null) {
                    val list = listOf(
                            data.img1, data.img2, data.img3

                    )
                    val adapter = DashBoardAdapter.getInstance(supportFragmentManager, list)

                    tab_layout.setupWithViewPager(pager, true)
                    pager.adapter = adapter

                    pager.setPageTransformer(true, DepthPageTransformer())


                    if (data.price.isEmpty()) {
                        Toast.makeText(
                                this,
                                "No record found for the barcode!!",
                                Toast.LENGTH_SHORT
                        ).show()
                        scan_image.visibility = View.VISIBLE
                        layout1.visibility = View.INVISIBLE
                        layout2.visibility = View.INVISIBLE
                        tool_bar.visibility = View.VISIBLE
                        spin_kit.visibility = View.GONE
                        scan_barcode.visibility = View.VISIBLE
                        mutableScannerTracker.value = false
                        return
                    }
                    mutableScannerTracker.value = true
                    model = data
                    viewVisible()
                    spin_kit.visibility = View.GONE
                    product_name.text = data.name
                    price.text = "₦${data.price}"
                    product_code.text = data.skuCode
                    description.text = data.description
                    scan_barcode.visibility = View.GONE


                }
            }
        } catch (ex: Exception) {
            delay5()

            ex.printStackTrace()
        }

    }

    override fun onBackPressed() {

        if (count == 0) {
            customToast(resources.getString(R.string.confirm_once_again))
            count++
        } else if (count == 1) {
            val dialog = ExitAppDialogFragment()
            dialog.show(supportFragmentManager, "exit_app_dialog")
        }
    }

    private fun customToast(name: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, custom_toast_container) as ViewGroup
        val displayMessage = layout.findViewById<TextView>(R.id.message)
        val toast = Toast(applicationContext)
        displayMessage.text = name
        toast.setGravity(Gravity.BOTTOM, 0, 64)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()

    }

    private fun productList(): ArrayList<HashMap<String, String>>? {
        val result = readJsonAsset("sotech.json")
        try {
            val obj = JSONObject(result)

            val m_jArry: JSONArray = obj.getJSONArray("products")
            val formList = ArrayList<HashMap<String, String>>()
            var m_li: HashMap<String, String>
            for (i in 0 until m_jArry.length()) {
                val jo_inside = m_jArry.getJSONObject(i)
                val description_value = jo_inside.getString("description")
                val name_value = jo_inside.getString("name")
                val product_code_value = jo_inside.getString("skuCode")
                val price_value = jo_inside.getString("price")
                val img1_value = jo_inside.getString("img1")
                val img2_value = jo_inside.getString("img2")
                val img3_value = jo_inside.getString("img3")


                //Add your values in your `ArrayList` as below:
                m_li = HashMap()
                m_li["name"] = name_value
                m_li["description"] = description_value
                m_li["skuCode"] = product_code_value
                m_li["price"] = price_value
                m_li["img1"] = img1_value
                m_li["img2"] = img2_value
                m_li["img3"] = img3_value


                formList.add(m_li)

            }
            return formList
        } catch (e: JSONException) {

            e.printStackTrace()
        }
        return null
    }

    private fun getDetails(result: String?): Model? {


        var dataCapture = Model()
        val list: ArrayList<HashMap<String, String>> = productList()!!
        if (result!!.isNotEmpty()) {

            for (map: HashMap<String, String> in list) {
                for ((key, value) in map.entries) {
                    if (key == "skuCode") {
                        if (value == result) {
                            val name = map["name"]
                            val price = map["price"]
                            val description = map["description"]
                            val img1 = map["img1"]
                            val img2 = map["img2"]
                            val img3 = map["img3"]
                            val skuCode = map["skuCode"]
                            dataCapture.name = name!!
                            dataCapture.price = price!!
                            dataCapture.description = description!!
                            dataCapture.img1 = img1!!
                            dataCapture.img2 = img2!!
                            dataCapture.img3 = img3!!
                            dataCapture.skuCode = skuCode!!
                            isTracking = true
                        } else {
                            isTracking = false
                        }
                    }
                }
            }

        }
        return dataCapture

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
       val menuItem = menu?.findItem(R.id.search)
        mutableScannerTracker.observeForever {

            if (it){
                runOnUiThread {   menuItem?.isVisible = true }
            }else{
                runOnUiThread {   menuItem?.isVisible = false
                scan_barcode.visibility = View.VISIBLE
                }
            }
        }
invalidateOptionsMenu()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {

scanBarcode()
            }
            R.id.share -> {
                share()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun scanBarcode() {
        layout1.visibility = View.INVISIBLE
        layout2.visibility = View.INVISIBLE
        startActivity(Intent(this, LiveBarcodeScanningActivity::class.java))

    }

    override fun onResume() {
        super.onResume()
        try {
            if (model?.price == null) {
                scan_image.visibility = View.VISIBLE
            }
        } catch (exc: Exception) {
            exc.printStackTrace()
        }
        if (!Utils.allPermissionsGranted(this)) {
            Utils.requestRuntimePermissions(this)
        }
    }

    fun delay5() {
        Handler().postDelayed({
            spin_kit.visibility = View.INVISIBLE
            tool_bar.visibility = View.VISIBLE
            scan_image.visibility = View.VISIBLE
            layout1.visibility = View.INVISIBLE
            layout2.visibility = View.INVISIBLE
            scan_barcode.visibility = View.VISIBLE

        }, 3000)
    }

    fun viewVisible() {
        product_name.visibility = View.VISIBLE
        price.visibility = View.VISIBLE
        price_label.visibility = View.VISIBLE
        description.visibility = View.VISIBLE
        product_code.visibility = View.VISIBLE
        product_code_label.visibility = View.VISIBLE
        tool_bar.visibility = View.VISIBLE
        spin_kit.visibility = View.GONE
    }

    private fun share() {
        if (model != null) {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, model!!.name)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        } else {
            Toast.makeText(this, "Unable to Share, scan first", Toast.LENGTH_SHORT).show()
            tool_bar.visibility = View.VISIBLE
        }

    }

}