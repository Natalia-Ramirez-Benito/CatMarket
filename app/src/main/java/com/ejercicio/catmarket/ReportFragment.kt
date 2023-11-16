import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.ejercicio.catmarket.DBClass
import com.ejercicio.catmarket.databinding.FragmentReportBinding

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper: DBClass

    companion object {
        // Agregar un método estático para crear una nueva instancia del fragmento
        fun newInstance(username: String): ReportFragment {
            val fragment = ReportFragment()
            val args = Bundle()
            args.putString("username", username)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val view = binding.root

        dbHelper = DBClass(requireContext())

        binding.submitButton.setOnClickListener {
            saveReport()
        }

        return view
    }

    private fun saveReport() {
        val reportType = binding.reportTypeEditText.text.toString()
        val reportDescription = binding.reportDescriptionEditText.text.toString()
        val username = arguments?.getString("username") ?: ""
        // Validar que los campos no estén vacíos
        if (reportType.isNotEmpty() && reportDescription.isNotEmpty()) {
            // Guardar el informe en la base de datos
            dbHelper.saveReport(username, reportType, reportDescription)

            var ad = AlertDialog.Builder(requireContext())
            ad.setTitle("Mensaje")
            ad.setMessage("Se ha registrado su incidencia correctamente")
            ad.setPositiveButton("Ok", null)
            ad.show()

            // Limpiar los campos después de guardar
            binding.reportTypeEditText.text.clear()
            binding.reportDescriptionEditText.text.clear()
        } else {
            var ad = AlertDialog.Builder(requireContext())
            ad.setTitle("Error")
            ad.setMessage("No se ha guardar la incidencia.")
            ad.setPositiveButton("Ok", null)
            ad.show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}