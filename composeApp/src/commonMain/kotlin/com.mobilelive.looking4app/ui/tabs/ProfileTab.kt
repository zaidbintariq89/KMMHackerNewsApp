package com.mobilelive.looking4app.ui.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class FeatureList(
    val name: String,
    val listIcon: ImageVector
)

object ProfileTab : Tab {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ProfileScreen {

            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProfileScreen(onGoBack: () -> Unit) {
        Scaffold(
            modifier = Modifier.semantics {

            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Profile")
                    },
//                    navigationIcon = {
//                        IconButton(onClick = onGoBack) {
//                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                        }
//                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                )
            }
        ) { padding ->
            ProfileContent(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
            ) {
                TopProfileLayout()
                FooterContent()
            }
        }
    }

    @Composable
    fun ProfileContent(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(modifier) {
            content()
        }
    }

    @OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)
    @Composable
    fun TopProfileLayout() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(8),
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource("compose-multiplatform.xml"),//id = DrawableResourceIcon(MyIcons.AppIcon).id
                        tint = Color(207,0,0),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "Zaid",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Bio",
                            style = MaterialTheme.typography.labelMedium,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = "my_description",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }

    @Composable
    fun FooterContent() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(8),
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                //
                moreOptionsList.forEach {
                    MoreOptionsComp(it)
                }
            }
        }
    }

    private val moreOptionsList = listOf(
        FeatureList("Edit Profile", Icons.Filled.Edit),
        FeatureList("Add Services", Icons.Default.MiscellaneousServices),
    )

    @Composable
    fun MoreOptionsComp(featureList: FeatureList) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = featureList.listIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)
            ) {
                Text(
                    text = featureList.name,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Profile"
            val icon = rememberVectorPainter(Icons.Default.AccountBox)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

}