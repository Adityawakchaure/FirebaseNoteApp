package Firebase


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.google.firebase.database.FirebaseDatabase

class NoteViewModel:ViewModel() {
    val database=FirebaseDatabase.getInstance().getReference("Note")



    fun postData(notes:Notes,OnSuccess: ()-> Unit, OnFailuler:() -> Unit) {

        val uniqueId = database.push().key ?: return
        var notesss=notes.copy(uniqueId=uniqueId)
        database.child(uniqueId).setValue(notesss)
            .addOnSuccessListener {
                OnSuccess()
            }
            .addOnFailureListener {
                OnFailuler()
            }
    }


//    private var AllData = MutableStateFlow<List<Notes>>(emptyList())
//    val NoteAll : StateFlow<List<Notes>> get() = AllData.asStateFlow()
private val AllData = MutableLiveData<List<Notes>>()
    val NoteAll: LiveData<List<Notes>> get() = AllData

    fun getAllData(){
        database.get()
            .addOnSuccessListener { snapshot->
                val members = snapshot.children.mapNotNull {
                    it.getValue(Notes::class.java)
                }

                AllData.value = members
            }
            .addOnFailureListener {

            }
    }

    fun delData(id: String,onSucess:()->Unit,onFailuler:()->Unit) {

        database.child(id).removeValue()
            .addOnSuccessListener {
                getAllData()
                onSucess()
            }

            .addOnFailureListener { onFailuler() }
    }

    fun updateData(id: String?, updatedData: Notes,OnSucess:()->Unit,OnFailuler: () -> Unit) {
        if (id != null) {
            database.child(id).setValue(updatedData)
                .addOnSuccessListener {
                    OnSucess()
                }
                .addOnFailureListener {
                    OnFailuler()
                }
        }
    }

}