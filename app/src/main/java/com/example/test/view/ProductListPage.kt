package com.example.test.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.BaseViewModel


@Composable
fun ProductListPage(
    viewModel: BaseViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    val scrollableState = rememberLazyListState()

    LazyColumn(
        state = scrollableState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){

        uiState.value.response?.forEach { product ->

            item{
                ItemView(
                    id = product.id,
                    name = product.name,
                    rating = product.rating,
                    price = product.price,
                    image = product.image,
                    count = product.count,
                    onCountDecreased = {
                        viewModel.updateItemCount(it, product.id!!)
                    },
                    onCountIncreased = {
                        viewModel.updateItemCount(it, product.id!!)
                    },
                )
            }
        }
    }

}

@Composable
private fun ItemView(
    modifier: Modifier = Modifier,
    id: Int? = null,
    name: String? = null,
    rating: String? = null,
    price: String? = null,
    image: String? = null,
    count: Int? = null,
    onCountDecreased: (count: Int) -> Unit = {},
    onCountIncreased: (count: Int) -> Unit = {},
){

    Row(
        modifier = modifier
            .fillMaxWidth()
    ){

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            name?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    fontSize = 24.sp,
                )
            }

            price?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    fontSize = 20.sp,
                )
            }

            rating?.let {
                Text(
                    text = it,
                    color = Color.Black,
                    fontSize = 20.sp,
                )
            }
        }

        Count(
            modifier = Modifier,
            count = count!!,
            onCountDecreased = {
                onCountDecreased(it)
            },
            onCountIncreased = {
                onCountIncreased(it)
            },
        )


    }
}

@Composable
private fun Count(
    modifier: Modifier = Modifier,
    count : Int = 0,
    onCountDecreased: (count: Int) -> Unit = {},
    onCountIncreased: (count: Int) -> Unit = {},
){
    var itemCount = count
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){

        Button(
            onClick = {
                itemCount -= 1
                onCountDecreased(itemCount)
            }
        ) {
            Text(
                text = "Dec",
                color = Color.Black,
                fontSize = 20.sp,
            )
        }

        Text(
            text = count.toString(),
            color = Color.Black,
            fontSize = 20.sp,
            )

        Button(
            onClick = {
                itemCount += 1
                onCountIncreased(itemCount) }
        ) {
            Text(
                text = "Inc",
                color = Color.Black,
                fontSize = 20.sp,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewItemView(){
    ItemView(
        image = "https://img.photographyblog.com/reviews/kodak_pixpro_fz201/photos/kodak_pixpro_fz201_01.jpg",
        name = "Tomato",
        price = "121",
        rating = "3"
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCount(){
    Count()
}
