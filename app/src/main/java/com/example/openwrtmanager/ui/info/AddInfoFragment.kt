package com.example.openwrtmanager.ui.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.openwrtmanager.R
import com.example.openwrtmanager.databinding.FragmentAddInfoBinding
import com.example.openwrtmanager.ui.device.DeviceViewModel

class AddInfoFragment : Fragment() {

    private val TAG = AddInfoFragment::class.qualifiedName
    private var _binding: FragmentAddInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddInfoViewModel
    private lateinit var deviceViewModel: DeviceViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddInfoViewModel::class.java)
        // TODO: Use the ViewModel
//        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
//        val deviceItemRepo = DeviceItemRepository(todoItemDb)
////        val identityItemRpo = IdentityItemRepository(todoItemDb)
//        val viewModelFactory = AnyViewModelFactory {
////            DeviceViewModel(deviceItemRepo, identityItemRpo)
//        }
//        deviceViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
//            DeviceViewModel::class.java
//        )
//        deviceViewModel.todoLiveData.observe(
//            viewLifecycleOwner,
//            Observer { todos: List<DeviceItem> ->
//                val items: Array<String> = todos.map { it ->
//                    it.displayName
//                }.toTypedArray()
//                Log.d(TAG, "onActivityCreated: "+items.get(0))
//                setUpSpnDevice(items)
//            })
//        binding.save.setOnClickListener {
//            Toast.makeText(context, binding.overviewItem.selectedItem.toString(), Toast.LENGTH_SHORT).show()
//        }
//
//        setUpOverwriteItem()
    }

    fun setUpOverwriteItem(){
        val list : MutableList<String> = ArrayList()
        list.add("System Info")
        list.add("Network Status")
        list.add("Network Traffic")
        list.add("WIFI Status")
        list.add("DHCP Leases")
        list.add("Active Connections")

        val adapter = ArrayAdapter(requireActivity(), R.layout.support_simple_spinner_dropdown_item, list)
        binding.overviewItem.adapter = adapter

        binding.overviewItem.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = list[p2]
                Toast.makeText(context, "$item selected", Toast.LENGTH_SHORT).show()
                binding.txt.setMovementMethod(ScrollingMovementMethod())
                when (p2) {
                    0 -> binding.txt.setText("Hola")
                    1 -> binding.txt.setText("1. Te ves mejor sonriendo\n2. Las mejores Cajas padan citando feliz\n3. No hay Por que desperdiciar el tiempo estando triste.\n4. Hay más razones para sonreir.\n5. To sistema inmunológico se resiente y puedes enfermarte.\n6. Mayores Probabilidades a subir de Peso.\n7. La tristeza afecta a tu piel.\n8. creas arrugas.\n9. Tienes incapacidad de concentracion\n10. Nadie merece tus lágrimas, quien se evitara hacerte llorar.\n11. Sonrie porque sucedió, ahora saber que no hacer y que hacer.\n12. Las mejores coas suceden cuando menos las esperas\n13. Todo Pasa por algo, hay que estar feliz por ello.\n14. Date cuenta de lo afortunada que eres.\n15. Recuerda que hay gente que te quiere.\n16. Yo te quiero\n17. Yo estoy triste sabiendo que estas triste.\n18. No quiero que este triste.\n19. Quiero que tengas recuerdos felices.\n20. Felices como nuestras recuerdos, salidas llamadas, desvelados.\n21. Un recuerdo es mejor estando feliz.\n22. Ese momento no cambiará, Procura que sea alegre\n23. Hay 29 bys en el día, no las desperdicio llorando.\n24. Vienen mejores cosas en ei futuro.\n25. La comida sabe mejor feliz.\n26. una carta se lee mejor feliz.\n27. si lloras mojas esta carta.\n28. una carta mojada es débil y se rompe.\n29. No quiero que dañes to regalo\n30. No quiero verte lastimado\n31. Quiero verte feliz"
                    )
                    else -> binding.txt.setText("otherwise")
                }
            }
        }


        limitDropDownHeight(binding.spnDevice)
    }

    fun setUpSpnDevice(list: Array<String>) {
//        val list : MutableList<String> = ArrayList()
//        list.add("Inicio")
//        list.add("31 cosas por las que no estar triste")
//        list.add("Random")

        val adapter = ArrayAdapter(requireActivity(), R.layout.support_simple_spinner_dropdown_item, list)
        binding.spnDevice.adapter = adapter

        binding.spnDevice.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = list[p2]
                Toast.makeText(context, "$item selected", Toast.LENGTH_SHORT).show()
                binding.txt.setMovementMethod(ScrollingMovementMethod())
                when (p2) {
                    0 -> binding.txt.setText("Hola")
                    1 -> binding.txt.setText("1. Te ves mejor sonriendo\n2. Las mejores Cajas padan citando feliz\n3. No hay Por que desperdiciar el tiempo estando triste.\n4. Hay más razones para sonreir.\n5. To sistema inmunológico se resiente y puedes enfermarte.\n6. Mayores Probabilidades a subir de Peso.\n7. La tristeza afecta a tu piel.\n8. creas arrugas.\n9. Tienes incapacidad de concentracion\n10. Nadie merece tus lágrimas, quien se evitara hacerte llorar.\n11. Sonrie porque sucedió, ahora saber que no hacer y que hacer.\n12. Las mejores coas suceden cuando menos las esperas\n13. Todo Pasa por algo, hay que estar feliz por ello.\n14. Date cuenta de lo afortunada que eres.\n15. Recuerda que hay gente que te quiere.\n16. Yo te quiero\n17. Yo estoy triste sabiendo que estas triste.\n18. No quiero que este triste.\n19. Quiero que tengas recuerdos felices.\n20. Felices como nuestras recuerdos, salidas llamadas, desvelados.\n21. Un recuerdo es mejor estando feliz.\n22. Ese momento no cambiará, Procura que sea alegre\n23. Hay 29 bys en el día, no las desperdicio llorando.\n24. Vienen mejores cosas en ei futuro.\n25. La comida sabe mejor feliz.\n26. una carta se lee mejor feliz.\n27. si lloras mojas esta carta.\n28. una carta mojada es débil y se rompe.\n29. No quiero que dañes to regalo\n30. No quiero verte lastimado\n31. Quiero verte feliz"
                    )
                    else -> binding.txt.setText("otherwise")
                }
            }
        }


        limitDropDownHeight(binding.spnDevice)
    }
    fun limitDropDownHeight(spnDevice: Spinner){
        val popup = Spinner::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true

        val popupWindow = popup.get(spnDevice) as ListPopupWindow
        popupWindow.height = (100 * resources.displayMetrics.density).toInt()
    }

}