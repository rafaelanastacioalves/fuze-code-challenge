
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.rafaelanastacioalves.fuzecodechallenge.domain.entities.Match
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

@Composable
fun EntityDetailsCompose(viewModel: MatchDetailViewModel) {
    val entityDetailsResource by viewModel.entityDetails.observeAsState()
    entityDetailsResource?.let { resource ->
        when(resource.status){
            Resource.Status.SUCCESS -> resource.data?.let { EntityDetailContent(entityDetail = it) }
            Resource.Status.INTERNAL_SERVER_ERROR -> TODO()
            Resource.Status.GENERIC_ERROR -> TODO()
            Resource.Status.LOADING -> Unit
        }
    }
}

@Composable
private fun EntityDetailContent(entityDetail: Match) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = entityDetail.name ?: "")
        }
    }

}


